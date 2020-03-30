package nest.hava.edutills.traverse;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.epopcon.eums.exception.PException;
import com.epopcon.wspider.common.code.WSErrorCode;
import com.epopcon.wspider.common.util.URLUtils;

public class Leaf {
	
	String name;
	transient String url;
	int depth=0;
	boolean bestItem=false;
	boolean specialExhibition=false;
	transient List<String> parentLeafName;
	transient Leaf parentLeaf;
	transient List<Leaf> childrenLeaf;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public List<Leaf> getChildLeaf() {
		return childrenLeaf;
	}

	public void setChildLeaf(List<Leaf> childrenLeaf) {
		this.childrenLeaf = childrenLeaf;
	}
	
	public static Leaf make(String name,String url)
	{
		return make(name,url,false,false);
	}
	
	public static Leaf make(String name,String url,boolean bestItem, boolean specialExhibition)
	{
		Leaf leaf = new Leaf();
		
		leaf.setName(name);
		leaf.setUrl(URLUtils.remove(url));
		leaf.setBestItem(bestItem);
		leaf.setSpecialExhibition(specialExhibition);
		
		return leaf;
		
	}
	
	public boolean isBestItem() {
		return bestItem;
	}

	public void setBestItem(boolean bestItem) {
		this.bestItem = bestItem;
	}

	public boolean isSpecialExhibition() {
		return specialExhibition;
	}

	public void setSpecialExhibition(boolean specialExhibition) {
		this.specialExhibition = specialExhibition;
	}

	/**
	 * get all names of this leaf, consist of parent names and current leaf name
	 * @return
	 */
	public List<String> getLeafNames()
	{
		List<String> ppList = ListUtils.copy(parentLeafName);
		ppList.add(getName());
		
		return ppList;
	}
	
	public void addParentLeafName(String parentName)
	{
		if(parentLeafName ==null)
		{
			parentLeafName = new ArrayList<>();
		}
		
		parentLeafName.add(parentName);
	}
	
	public List<String> getParentLeafNames()
	{
		return parentLeafName;
	}
	
	public Leaf getParentLeaf() {
		return parentLeaf;
	}

	public void setParentLeaf(Leaf parentLeaf) {
		this.parentLeaf = parentLeaf;
	}

	public boolean isLastLeaf()
	{
		if(childrenLeaf ==null || childrenLeaf.isEmpty())
		{
			return true;
		}
		
		return false;
	}
	
	public Leaf getLeaf(String leafName)
	{
		Leaf m = getLeaf(leafName, true);
		
		if(m==null)
		{
			m = getLeaf(leafName, false);
		}
		
		return m;
	}
	
	
	public Leaf getLeaf(String leafName,boolean direct)
	{
		Leaf m;
		if(childrenLeaf != null)
		{
			List<Leaf> children = childrenLeaf;
			
			if(children !=null && !children.isEmpty())
			{
				for(Leaf ff:children)
				{
					if(ff.getName().equals(leafName))
					{
						return ff;
					}else {
						if(!direct)
						{
							m=getChildLeaf(ff, leafName);
							if(m!=null)
							{
								return m;
							}
						}
						
					}
				}
			}
			
		}
		
		return null;
	}
	
	
	private Leaf getChildLeaf(Leaf sourceLeaf, String leaf)
	{
		List<Leaf> children = sourceLeaf.getChildLeaf();
		Leaf m;
		if(children !=null && !children.isEmpty())
		{
			for(Leaf ff:children)
			{
				if(ff.getName().equals(leaf))
				{
					return ff;
					
				}else {
					m = getChildLeaf(ff, leaf);
					
					if(m!=null)
					{
						return m;
					}
				}
			}
		}
		
		return null;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leaf other = (Leaf) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	/**
	 * 
	 * @param str
	 * @param url
	 * @param bestItem
	 * @param specialExhibition
	 */
	public boolean addChildLeaf(LeafTraverse lft, String str , String url,boolean bestItem, boolean specialExhibition)
	{
		Leaf lf = Leaf.make(str, url, bestItem, specialExhibition);
		
		if(lft!=null)
		{
			if(!lft.findSameLeaf(lf))
			{
				addChildLeaf(lf,lft);
				return true;
			}
		}else {
			addChildLeaf(lf,lft);
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param str
	 * @param url
	 * @param bestItem
	 * @param specialExhibition
	 */
	public void addChildLeaf(String str , String url,boolean bestItem, boolean specialExhibition)
	{
		addChildLeaf(null,str,url,bestItem,specialExhibition);
	}
	
	public void addChildLeaf(Leaf f,LeafTraverse lft)
	{
		if(f !=null)
		{
			if(childrenLeaf == null)
			{
				childrenLeaf = new ArrayList<>();
			}
			
			if(f.getDepth()==0)
			{
				//depth를 보정한다.
				f.setDepth(this.depth + 1);
			}
			
			if(getParentLeafNames() !=null && ! getParentLeafNames().isEmpty())
			{
				for(String s: getParentLeafNames())
				{
					f.addParentLeafName(s);
				}
				
			}
			
			f.addParentLeafName(this.getName());
			childrenLeaf.add(f);
			f.setParentLeaf(this);
			
			if(lft != null)
			{
				if(lft.target(f))
				{
					throw PException.buildException(WSErrorCode.GOODS_TARGET_CATEGORY, "Target Category");
				}
			}
			
		}
	}
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE,false).toString();
	}
	

}
