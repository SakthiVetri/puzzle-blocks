# puzzle-blocks

Finds maximum height by stacking the given blocks with the constraint that a block can be stacked on another only if its height, width and length are smaller or same as the one below it.

# How to run

Execute  "mvn clean install" at root directory to create jar.

Execute 
java -jar target/blocks-1.0-SNAPSHOT-jar-with-dependencies.jar


Above command will read block dimensions from commandline.  Please enter one block dimensions per line.  
Please enter three comma separated integers for each block dimensions.  Enter exit to mark end of blocks.

# Example
$java -jar target/blocks-1.0-SNAPSHOT-jar-with-dependencies.jar
Please enter dimensions as one block per line. Please enter the values as 3 comma separated integers (1,2,3) for each block.  Type exit to end.
1,3,2
2,3,1
3,2,1
exit
