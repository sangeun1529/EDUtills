package nest.hava.edutills.traverse;

public class TargetLeaf implements Filter {
	
	String filter="";
	
	
	private TargetLeaf() {
	}
	
	public static TargetLeaf make(String filter)
	{
		TargetLeaf aa = new TargetLeaf();
		aa.setFilterString(filter);
		
		return aa;
	}
	
	
	public boolean match(String filter)
	{
		if(this.filter.equalsIgnoreCase(filter)) return true;
		return false;
	}
	
	public void setFilterString(String filter) {
		this.filter = filter;
	}

	@Override
	public String getFilterString() {
		return filter;
	}

}
