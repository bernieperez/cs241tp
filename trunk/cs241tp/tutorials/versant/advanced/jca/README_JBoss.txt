Steps to deploy the JVI JCA Resource Adapter to JBoss 4.0.x
1) Add the jar dependency $VERSANT_ROOT/lib/jvi7.0.1-jdk1.4.jar.
   You can copy this file in the lib directory of the domain 
   ($JBOSS_HOME/server/default/lib}
   or make sure that jvi7.0.1-jdk1.4.jar is in the CLASSPATH before the JBoss server is started.

2) You can configure the database specific information and other details in ra.xml of the JCA adapter rar file before you deploy the adapter.
   For example,
   <config-property>
      <config-property-name>ConnectionURL</config-property-name>
      <config-property-type>java.lang.String</config-property-type>
      <config-property-value>voddb</config-property-value>
    </config-property>
    <config-property>
      <config-property-name>SessionOptions</config-property-name>
      <config-property-type>java.lang.String</config-property-type>
      <config-property-value>DEFAULT</config-property-value>
    </config-property> 

3) Start the JBoss server using 
   ${JBOSS_HOME}/bin/run.bat or ${JBOSS_HOME}/bin/run.sh

4) Deploy the JCA adapter rar file.
   You can copy the appropriate JVI JCA adapter rar file $VERSANT_ROOT/lib/jvi/jca/vodLOCAL.rar, $VERSANT_ROOT/lib/jvi/jca/vodNOTX.rar, or $VERSANT_ROOT/lib/jvi/jca/vodXA.rar to the deploy folder of your JBoss domain
   $JBOSS_HOME/server/default/deploy.
   Also copy the corresponding JBoss specific descriptors $VERSANT_ROOT/lib/jvi/jca/jboss/vodjvi_jca_local-ds.xml, $VERSANT_ROOT/lib/jvi/jca/jboss/vodjvi_jca_notx-ds.xml, or $VERSANT_ROOT/lib/jvi/jca/jboss/vodjvi_jca_xa-ds.xml to the deploy folder of your JBoss domain $JBOSS_HOME/server/default/deploy.

Steps to build and run the SMP Stateless and SMP Stateful examples using the JVI JCA Resource Adapter
1) Configure these common properties in $VERSANT_ROOT/demo/jvi/advanced/jca/common.properties
   DB_NAME=voddb
   Create the database specified in common.properties before running the application
   makedb voddb
   createdb voddb

2)  To run the demo on 64 bit platforms please add followng line to target "setupdb" in  $VERSANT_ROOT/demo/jvi/advanced/jca/setup.xml
  <jvmarg value="-d64"/>

3) Configure these properties in $VERSANT_ROOT/demo/jvi/advanced/jca/jboss.properties as per your configuration.
eg #JBoss Home Directory
   JBOSS_HOME=E:/jboss-4.0.4.GA
   JBOSS_DEPLOY_DIR=${JBOSS_HOME}/server/default/deploy

4) To build and run the SMP stateless bean example:
   cd $VERSANT_ROOT/demo/jvi/advanced/jca/smp/stateless
   "ant -f build_jboss.xml deploy" will compile, enhance all the sources and deploy the generated ear file.
   "ant -f build_jboss.xml run" will run the client application.

5) To build and run the SMP stateful bean example:
   cd $VERSANT_ROOT/demo/jvi/advanced/jca/smp/stateful
   "ant -f build_jboss.xml deploy" will compile, enhance all the sources and deploy the generated ear file.
   "ant -f build_jboss.xml run" will run the client application.
