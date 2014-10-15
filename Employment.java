package rsgl;
class Employee
{
	private String id;
	private String name;
	private String grade;
	private String salary;
	
	public Employee(String aId,String aName,String aGrade,String aSalary)
	{
		this.id=aId;
		this.name=aName;
		this.grade=aGrade;
		this.salary=aSalary;
	}
	public String getId()
	{
		return id;
	}
	
	public String setId(String neuwId)
	{
		this.id=neuwId;
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String setName(String neuwName)
	{
		this.name=neuwName;
		return name;
	}
	
	public String getGrade()
	{
		return grade;
	}
	
	public String setGrade(String neuwGrade)
	{
		this.grade=neuwGrade;
		return grade;
	}
	
	public String getSalary()
	{
		return salary;
	}
	
	public String setSalary(String neuwSalary)
	{
		this.salary=neuwSalary;
		return salary;
	}
	
}