#How to compile:
mvn clean compile assembly:single

#How to repoduce
##with spoon.SpoonException 
java -cp target/1-spoon-interval-jar-with-dependencies.jar Main test/Mockito-800012b/src/org/mockito/ test/Mockito-800012.buggy.lines some.json
java -cp target/1-spoon-interval-jar-with-dependencies.jar Main test/Mockito-800012b/src/org/mockito/internal/util/reflection/GenericMetadataSupport.java test/Mockito-800012.buggy.lines some.json
##without spoon.SpoonException
cp test/Mockito-800012b/src/org/mockito/internal/util/reflection/* ./other-dir/
java -cp target/1-spoon-interval-jar-with-dependencies.jar Main ./other-dir/ test/Mockito-800012.buggy.lines some.json

