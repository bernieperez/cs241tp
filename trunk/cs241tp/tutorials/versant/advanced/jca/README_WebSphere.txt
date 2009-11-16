Steps to Deploy the JVI JCA resource adapter to WebSphere 6.1 
1) Make sure that WAS (WebSphere Application Server) is running.
   To start the WebSphere 6.1 server you can follow the "Start Server" link option from
   (Start -> Programs -> IBM WebSphere -> Application Server V 6.1 -> Profiles -> AppSrv01 -> FirstSteps)
   or use the command line option {WAS_HOME}/bin/startServer.bat <server> or
   {WAS_HOME}/bin/startServer.sh <server> 
  
2) Open the admin console of WAS in a web browser and add a Shared Library as follows: 
   Go to Environment - Shared Libraries
   Select Scope - select the Node and Server on which the Shared Library needs to be installed
   In Preferences - Click New
   Add the following details and Apply and save the changes:
   Name: JVIJCA
   Classpath: <VERSANT_ROOT>/lib/jvi7.0.1-jdk1.4.jar
   Native Library Path: <VERSANT_ROOT>/bin
    
3) Using the admin console install the JVI JCA local resource adapator as follows: 
   Go to Resources - Resource Adapters - Resource adapters
   Select scope - select the Node and Server on which the resource adaptor needs to be installed
   Click on the Install RAR button
   Add the follwing details and Apply and save the changes:
   Local path (to the rar file): select the file <VERSANT_ROOT>/lib/jvi/jca/vodLOCAL.rar
   Name: vodLOCAL
   Classpath: <VERSANT_ROOT>/lib/jvi7.0.1-jdk1.4.jar
    
   Once the Resource adaptor is created , install a J2C connection factory over the vodLocal resource adapter created as follows: 
   Click on J2C connection factory
   Add the following details and Apply and save the changes
   Name: vodLocalConnectionFactory
   Jndi name: eis/vodJNDILocal 

To run the JVI JCA example using WebSphere 6.1
Make sure that the PATH is set for running <WAS_HOME>/bin/ws_ant.(bat|sh). 
1) Configure these common properties in $VERSANT_ROOT/demo/jvi/advanced/jca/common.properties
   DB_NAME=voddb
   Create the database specified in common.properties before running the application
   makedb voddb
   createdb voddb

2)  To run the demo on 64 bit platforms please add followng line to target "setupdb" in  $VERSANT_ROOT/demo/jvi/advanced/jca/setup.xml
  <jvmarg value="-d64"/>

3) Modify these WebSphere properties in $VERSANT_ROOT/demo/jvi/advanced/jca/websphere.properties
   #WebSphere Home Directory
   WAS_HOME=C:/PROGRA~1/IBM/WebSphere/AppServer
   WAS_NODE=sysjavaw2kNode01
   WAS_CELL=sysjavaw2kNode01Cell
   #WebSphere server name in which the applications should be installed
   WAS_SERVER=server1
   #WebSphere virtual host name in which web applications should be installed
   VIRTUAL_HOST=default_host

4) NOTE: If "Enable Administrative Security" is checked at installation time then invocation of wsadmin script through build_ws.xml causes a window to display with a password prompt.
   This can be avoided by following the steps given in this IBM WebSphere URL: 
   http://www-1.ibm.com/support/docview.wss?&uid=swg21142299
   For a SOAP connection, edit the soap.client.props file:
   $WAS_HOME/profiles/AppSrv01/properties/soap.client.props
   Find the following two lines and add the appropriate user ID and password:
   com.ibm.SOAP.loginUserid=
   com.ibm.SOAP.loginPassword=

5) To build and run the SMP stateless bean example:
   cd $VERSANT_ROOT/demo/jvi/advanced/jca/smp/stateless
   "ws_ant -f build_ws.xml deploy" will compile, enhance all the sources and deploy the generated ear file.
   "ws_ant -f build_ws.xml run" will run the client application.

6) To build and run the SMP stateful bean example:
   cd $VERSANT_ROOT/demo/jvi/advanced/jca/smp/stateful
   "ws_ant -f build_ws.xml deploy" will compile, enhance all the sources and deploy the generated ear file.
   "ws_ant -f build_ws.xml run" will run the client application.

NOTE: On Windows, if you observe the VException(2994:SM_NOT_IN_USER_LIST) error while running your application, please make sure that the Log On properties for the service "IBM WebSphere Application Server V6.1" reflect your account settings.
