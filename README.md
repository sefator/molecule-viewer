
About
-----
This is simple molecule viewer for displaying .mol files via HTML. Uses JChem lib for drawing molecules and jQuery&Bootstrap for client side scripting/display.

Instalation
-----------
1. Download and unpack jchem-signed-lib-6.0.2.zip from http://www.chemaxon.com/download/jchem/jchem-for-java 
2. Use following commands to install required jars to your local maven repository.

    mvn install:install-file -Dfile=chemaxon-core.jar -DgroupId=chemaxon -DartifactId=core -Dversion=6.0.2 -Dpackaging=jar
    mvn install:install-file -Dfile=MarvinBeans.jar -DgroupId=chemaxon -DartifactId=MarvinBeans -Dversion=6.0.2 -Dpackaging=jar
    mvn install:install-file -Dfile=MarvinBeans-formats-mdl.jar -DgroupId=chemaxon -DartifactId=MarvinBeans-formats-mdl -Dversion=6.0.2 -Dpackaging=jar
    mvn install:install-file -Dfile=MarvinBeans-formats-cml.jar -DgroupId=chemaxon -DartifactId=MarvinBeans-formats-cml -Dversion=6.0.2 -Dpackaging=jar
    mvn install:install-file -Dfile=MarvinBeans-formats-image.jar -DgroupId=chemaxon -DartifactId=MarvinBeans-formats-image -Dversion=6.0.2 -Dpackaging=jar
    mvn install:install-file -Dfile=MarvinBeans-formats-peptide.jar -DgroupId=chemaxon -DartifactId=MarvinBeans-formats-peptide -Dversion=6.0.2 -Dpackaging=jar
    mvn install:install-file -Dfile=MarvinBeans-formats.jar -DgroupId=chemaxon -DartifactId=MarvinBeans-formats -Dversion=6.0.2 -Dpackaging=jar
    mvn install:install-file -Dfile=MarvinBeans-license.jar -DgroupId=chemaxon -DartifactId=MarvinBeans-license -Dversion=6.0.2 -Dpackaging=jar
    mvn install:install-file -Dfile=MarvinBeans-concurrent.jar -DgroupId=chemaxon -DartifactId=MarvinBeans-concurrent -Dversion=6.0.2 -Dpackaging=jar
    mvn install:install-file -Dfile=MarvinBeans-diverse-modules.jar -DgroupId=chemaxon -DartifactId=MarvinBeans-diverse-modules -Dversion=6.0.2 -Dpackaging=jar
    mvn install:install-file -Dfile=MarvinBeans-sketch.jar -DgroupId=chemaxon -DartifactId=MarvinBeans-sketch -Dversion=6.0.2 -Dpackaging=jar
3. Run 'mvn install'
