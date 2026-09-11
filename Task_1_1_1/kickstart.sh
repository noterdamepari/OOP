javac src/main/java/ru/nsu/*.java -d ./build
javadoc -d build/docs/javadoc -sourcepath src/main/java -subpackages ru.nsu
jar cfe out.jar ru.nsu.Main -C build ru
java -jar out.jar