Steps to deploy the JVI JCA Resource Adapter to WebLogic 9.x
1) Add the jar dependency $VERSANT_ROOT/jvi7.0.1-jdk1.4.jar.
   You can copy this file in the lib directory of the domain 
   ($BEA_HOME/user_projects/domains/$DOMAIN_NAME/lib)
   or make sure that jvi7.0.1-jdk1.4.jar is in the CLASSPATH before the WebLogic server is started.

2) You can configure the database specific information and other details in ra.xml of the JCA adapter
   rar file before you deploy the adapter.
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

3) Start the WebLogic server using 
   ${WL_USER_PROJECTS}/${WL_DOMAIN}/startWebLogic.cmd or ${WL_USER_PROJECTS}/${WL_DOMAIN}/startWebLogic.sh

4) Deploy the JCA adapter rar file.
   You can copy the appropriate JVI JCA adapter rar file $VERSANT_ROOT/lib/jvi/jca/vodLOCAL.rar, $VERSANT_ROOT/lib/jvi/jca/vodNOTX.rar, or $VERSANT_ROOT/lib/jvi/jca/vodXA.rar to the autodeploy folder of your Weblogic domain $BEA_HOME/user_projects/domains/$DOMAIN_NAME/autodeploy or you can deploy the rar file using the domain's admin server console.

Steps to build and run the SMP Stateless and SMP Stateful examples using the JVI JCA Resource Adapter
1) Configure these common properties in $VERSANT_ROOT/demo/jvi/advanced/jca/common.properties
   DB_NAME=voddb
   Create the database specified in common.properties before running the application: 
   makedb voddb
   createdb voddb

2)  To run the demo on 64 bit platforms please add followng line to target "setupdb" in  $VERSANT_ROOT/demo/jvi/advanced/jca/setup.xml
  <jvmarg value="-d64"/>

3) Configure these properties in $VERSANT_ROOT/jvi/demo/jca/weblogic.properties as per your configuration.
   eg JDK_HOME=E:/bea9.1/jdk150_04
   #WebLogic Home Directory
   WL_HOME=E:/bea9.1/weblogic91
   #WebLogic Domain Directory 
   WL_USER_PROJECTS=E:/bea9.1/user_projects/domains
   #WebLogic domain name
   WL_DOMAIN=base_domain
   APP_DIR=${WL_USER_PROJECTS}/${WL_DOMAIN}/autodeploy

   [Weblogic 9.x Configuration:
   You can create a new domain in Weblogic 9.x using the configuration wizard  
   (Start -> Programs -> BEA Products -> Tools -> Configuration Wizard)] 

4) To build and run the SMP stateless bean example:
   cd $VERSANT_ROOT/demo/jvi/advanced/jca/smp/stateless
   "ant -f build_wl.xml deploy" will compile, enhance all the sources and deploy the generated ear file.
   "ant -f build_wl.xml run" will run the client application.

5) To build and run the SMP stateful bean example:
   cd $VERSANT_ROOT/demo/jvi/advanced/jca/smp/stateful
   "ant -f build_wl.xml deploy" will compile, enhance all the sources and deploy the generated ear file.
   "ant -f build_wl.xml run" will run the client application.
   
NOTE: Instructions above assume that you are starting WebLogic server in development mode.
