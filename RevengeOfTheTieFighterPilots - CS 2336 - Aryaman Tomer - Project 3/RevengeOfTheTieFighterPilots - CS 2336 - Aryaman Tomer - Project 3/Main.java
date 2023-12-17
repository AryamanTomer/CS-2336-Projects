//axt210052 Aryaman Tomer CS2336.006 Ranran Feng
import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Prompt for the name of the file containing the map of the galaxy
        System.out.print("Enter the name of the galaxy map file: ");
        String galaxyMapFileName = input.nextLine();


        //Prompt for the name of the file containing the pilot routes
        System.out.print("Enter the name of the pilot routes file: ");
        String pilotRoutesFileName = input.nextLine();

        //Create the graph and populate it from the galaxy map file
        Graph galaxyMap = readGalaxyMap(galaxyMapFileName);

        //Analyze pilot routes and write the results to the output file
        analyzePilotRoutes(pilotRoutesFileName, galaxyMap);
        input.close();
    }

    //Function to go through all the points in the galaxy map
    private static Graph readGalaxyMap(String fileName){
        Graph galaxyMap = new Graph();
        //Going through the galaxy map file
        try(BufferedReader r = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = r.readLine()) != null){
                //Splitting each planet and distance
                String[] parts = line.split(" ");
                String source = parts[0];

                for(int i = 1; i < parts.length; i++){
                    //Splitting the planet and the distance
                    //The distance is the weight
                    String[] edge = parts[i].split(",");
                    String destination = edge[0];
                    int weight = Integer.parseInt(edge[1]);
                    //The edge is inserted into the galaxyMap graph
                    galaxyMap.insertEdge(source, destination, weight);
                }
            }
            //Exception when the file is not found
        } catch(IOException e){
            e.printStackTrace();
        }

        return galaxyMap;
    }
    //Analyze the pilot routes and write the results to the output files
    private static void analyzePilotRoutes(String fileName, Graph galaxyMap){
        List<String> results = new ArrayList<>();
        //Reading each line of the pilot routes
        try(BufferedReader r = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = r.readLine()) != null){
                //Splitting each line to get the pilot name and the patrol routes
                String[] parts = line.split(" ");
                String pilotName = parts[0];
                List<String> planets = Arrays.asList(Arrays.copyOfRange(parts, 1, parts.length));
                //Calculating the path weight and checking it's validation
                int pathWeight = calculatePathWeight(galaxyMap, planets);
                boolean isValid = pathWeight > 0;
                results.add(pilotName + "\t" + pathWeight + "\t" + (isValid ? "valid" : "invalid"));
                //Adding the result to the list
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        //Sorting the results and writing to the output file
        Collections.sort(results);
        writeResultsToFile(results, "patrols.txt");
    }
    //Calculate the path weight for a given patrol route
    private static int calculatePathWeight(Graph galaxyMap, List<String> planets){
        int pathWeight = 0;
        for(int i =0; i < planets.size() - 1; i++){
            String source = planets.get(i);
            String destination = planets.get(i + 1);
            //Get edges for the source vertex
            Map<String, Integer> edges = galaxyMap.getAdjacencyList().getOrDefault(source, Collections.emptyMap());
            //Check if there is an edge to the destination
            if(edges.containsKey(destination)){
                pathWeight += edges.get(destination);
            } else {
                //The path is invalid as there is no edge between planets
                return 0;
            }
        }
        return pathWeight;
    }

    private static void writeResultsToFile(List<String> results, String fileName){
        //Reading the input file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for(String result: results){
                writer.write(result);
                writer.newLine();
            }
            //Exception when the file is not found
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}