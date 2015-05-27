package fr.exagone.files.xls.bean;

public class Employee {

	   	private int id;
	    private String name;
	    private double salary;


	    public Employee(int id, String name,double salary){
	        this.id = id;
	        this.name = name;
	        this.salary = salary;
	    }
	   
	    public Employee() {
	    }

	    /**
	     * @return the id
	     */
	    public int getId() {
	        return id;
	    }
	    /**
	     * @param id the id to set
	     */
	    public void setId(int id) {
	        this.id = id;
	    }
	    /**
	     * @return the name
	     */
	    public String getName() {
	        return name;
	    }
	    /**
	     * @param name the name to set
	     */
	    public void setName(String name) {
	        this.name = name;
	    }
	   
	    /**
	     *
	     * @return salary
	     */
	    public double getSalary() {
	        return salary;
	    }
	   
	    /**
	     *
	     * @param salary
	     */
	    public void setSalary(double salary) {
	        this.salary = salary;
	    }
	
}
