//Aryaman Tomer axt210052
class Driver implements Comparable<Driver> {
    private String driverName;
    private double driverArea;
    private static boolean sortVariable;
    //Constructor with parameters
    public Driver(String name, double area){
        this.driverName = name;
        this.driverArea = area;
    }
    //Constructor without parameters
    public Driver(){
        driverName = "";
        driverArea = 0;
    }
    //Settings the name
    public void setName(String n){
        driverName = n;
    }
    //Setting the area
    public void setArea(double a){
        driverArea = a;
    }
    //Setting the sort
    public void setSort(boolean s){
        sortVariable = s;
    }
    //Obtaining the driverName
    public String getName(){
        return driverName;
    }
    //Obtaining the driverArea
    public double getArea(){
        return driverArea;
    }
    @Override
    public String toString() {
        return driverName + " " + (int)driverArea + "\n";
    }
    //Comparing one driver to another to get comparison values
    public int compareTo(Driver d){
        //Comparing 2 drivers
        String nameOne = this.driverName;
        double areaOne = this.driverArea;
        String nameTwo = d.getName();
        double areaTwo = d.getArea();
        //The comparison of the names and area of one driver to another to verify integrity of data
        if(nameOne.equals(nameTwo) && areaOne == areaTwo){
            return 0;
        }
        else if(!nameOne.equals(nameTwo) && areaOne == areaTwo){
            if(nameOne.length() < nameTwo.length()){
                return -1;
            } else {
                return 1;
            }
        }
        else if(nameOne.equals(nameTwo) && areaOne != areaTwo){
            if (areaOne < areaTwo){
                return -2;
            }
            else if(areaOne > areaTwo){
                return 2;
            }
            else{
                return 0;
            }
        }
        else{
            if(nameOne.length() < nameTwo.length() && areaOne < areaTwo){
                return -3;
            }
            else{
                return 3;
            }
            
        }

    }


}

