package nest.hava.edutills.traverse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.epopcon.wspider.common.data.extract.Category;
import com.epopcon.wspider.common.logger.Logger;
import com.epopcon.wspider.common.util.StringUtils;

/**
 * web mining을 할때  tree 형식으로 계층형 데이타를 구성할때 유용한 클래스
 * @author BaeMunHwan
 *
 */
public class LeafTraverse {
	
	private Leaf rootLeaf;
	private TargetLeaf tLeaf;
	private LeafTraverse(){}
	
	
	public void makeRootLeaf(String name)
	{
		Leaf root = new Leaf();
		root.setName(name);
		root.setDepth(0);
		root.setUrl("");
		
		this.rootLeaf = root;
	}
	
	
	public LeafTraverse setTargetLeaf(String targetLeaf)
	{
		targetLeaf = targetLeaf.replaceAll(" ", "");
		TargetLeaf pf = TargetLeaf.make(targetLeaf);
		tLeaf = pf;
		
		return this;
	}
	
	
	public LeafTraverse addChildLeaf(Leaf childLeaf)
	{
		rootLeaf.addChildLeaf(childLeaf,this);
		
		return this;
	}
	
	
	public static LeafTraverse make(String siteName)
	{
		LeafTraverse lt = new LeafTraverse();
		lt.makeRootLeaf(siteName);
		
		return lt;
	}
	
	
	public List<Leaf> getFirstDepthChildLeaves()
	{
		List<Leaf> children = rootLeaf.getChildLeaf();
		
		return children;
	}
	
	public boolean target(Leaf l)
	{
		String query = l.getName();
		boolean a = false;
		
		
		if(tLeaf!=null && tLeaf.match(query)) a =  true;
		
		
		if(!a && l!=null)
		{
			return hierarchyFilter(query,l);
			
		}else {
			return a;
		}
	}
	
	private boolean hierarchyFilter(String query,Leaf f)
	{
		List<String> pNameList = f.getParentLeafNames();
		
		if(pNameList!=null)
		{
			StringBuffer sb = new StringBuffer();
			String str;
			//root node 제외
			for(int i=1; i< pNameList.size();i++)
			{
				str = pNameList.get(i);
				sb.append(str);
				sb.append(">");
			}
			sb.append(f.getName());
			
			String tt = sb.toString();
			tt = tt.replaceAll(" ", "");
			boolean a = false;
			
			
			if(tLeaf!=null && tLeaf.match(tt)) a = true;
			return a;
			
		}
		
		return false;
		
	}
	
	
	/**
	 * 현재 입력할 leaf와 같은 이름과 url이 같은 것이 존재하는지 검사한다
	 * 단,url이 없는 경우는 검사에서 제외한다.
	 * @param 
	 * @return
	 */
	public boolean findSameLeaf(Leaf l)
	{
		if(!StringUtils.isNullOrEmpty(l.getUrl()))
		{
			return findSameLeaf(rootLeaf, l);
		}
		
		return false;
	}
	
	private boolean findSameLeaf(Leaf leaf,Leaf targetLeaf)
	{
		List<Leaf> children = leaf.getChildLeaf();
		
		if(children !=null && !children.isEmpty())
		{
			for(Leaf l : children)
			{
				if(l.equals(targetLeaf))
				{
					return true;
				}
				
			}
		}
		
		return false;
	}
	
	
	public List<Leaf> getChildLeaves(String leaf)
	{
		List<Leaf> children = rootLeaf.getChildLeaf();
		List<Leaf> m = null;
		if(children !=null && !children.isEmpty())
		{
			for(Leaf ff:children)
			{
				if(ff.getName().equals(leaf))
				{
					return ff.getChildLeaf();
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
	
	private List<Leaf> getChildLeaf(Leaf sourceLeaf, String leaf)
	{
		List<Leaf> children = sourceLeaf.getChildLeaf();
		List<Leaf> m = null;
		if(children !=null && !children.isEmpty())
		{
			for(Leaf ff:children)
			{
				if(ff.getName().equals(leaf))
				{
					return ff.getChildLeaf();
					
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
	
	private String depthToSpace(int depth)
	{
		StringBuffer sb = new StringBuffer();
		
		for(int i=0 ; i< depth;i++)
		{
			sb.append("\t");
		}
		
		return sb.toString();
			
	}
	
	public void traverse(Logger logger)
	{
		StringBuffer sb = new StringBuffer(rootLeaf.getName()).append("\n");
		
		List<Leaf> oneDepth =  rootLeaf.getChildLeaf();
		
		if(oneDepth !=null && !oneDepth.isEmpty())
		{
			for(Leaf l1 : oneDepth)
			{
				sb.append(depthToSpace(l1.getDepth())).append(l1.getName()).append("\n");
				traverse(sb,l1);
			}
		}
		
		logger.info("LeafTraverse", sb.toString());
		
	}
	
	public void traverseV1(Logger logger)
	{
		StringBuffer sb = new StringBuffer(rootLeaf.getName()).append("\n");
		
		List<Leaf> oneDepth =  rootLeaf.getChildLeaf();
		
		if(oneDepth !=null && !oneDepth.isEmpty())
		{
			for(Leaf l1 : oneDepth)
			{
				sb.append(depthToSpace(l1.getDepth())).append(l1.getName());
				traverseV1(sb,l1);
			}
		}
		
		logger.info("LeafTraverse", sb.toString());
		
	}
	
	public void traverse(OutputStream out)
	{
		StringBuffer sb = new StringBuffer(rootLeaf.getName()).append("\n");
		
		List<Leaf> oneDepth =  rootLeaf.getChildLeaf();
		
		if(oneDepth !=null && !oneDepth.isEmpty())
		{
			for(Leaf l1 : oneDepth)
			{
				sb.append(depthToSpace(l1.getDepth())).append(l1.getName()).append("\n");
				traverse(sb,l1);
			}
		}
		
		try {
			out.write(sb.toString().getBytes());
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void traverseV1(StringBuffer sb, Leaf leaf)
	{
		List<Leaf> children = leaf.getChildLeaf();
		boolean first = true;
		
		if(children !=null && !children.isEmpty())
		{
			for(Leaf l : children)
			{
				if(first)
				{
					sb.append(depthToSpace(1)).append(l.getName());
					first = false;
					
				}else {
					sb.append(depthToSpace(l.getDepth())).append(l.getName());
				}
				
				if(!l.isLastLeaf())
				{
					traverseV1(sb,l);
				}else {
					sb.append("\n");
				}
			}
		} else {
			sb.append("\n");
		}
	}
	
	private void traverse(StringBuffer sb, Leaf leaf)
	{
		List<Leaf> children = leaf.getChildLeaf();
		
		if(children !=null && !children.isEmpty())
		{
			for(Leaf l : children)
			{
				sb.append(depthToSpace(l.getDepth())).append(l.getName()).append("\n");
				if(!l.isLastLeaf())
				{
					traverse(sb,l);
				}
			}
		}
	}
	
	/**
	 * get a list of general category 
	 * @return
	 */
	public List<Category> toCategoryList()
	{
		List<Leaf> oneDepth =  rootLeaf.getChildLeaf();
		List<Category> cateList = new ArrayList<>();
		
		if(oneDepth !=null && !oneDepth.isEmpty())
		{
			for(Leaf l1 : oneDepth)
			{
				if(l1.isLastLeaf() && !StringUtils.isNullOrEmpty(l1.getUrl()))
				{
					Category cate = new Category();
					cate.setCategoryNames(l1.getLeafNames());
					cate.setCategoryUrl(l1.getUrl());
					cateList.add(cate);
				}else if(l1.isLastLeaf()) {
					Leaf t = getLastLeafWithUrl(l1.getParentLeaf());
					if(t != null) {
						Category cate = new Category();
						cate.setCategoryNames(t.getLeafNames());
						cate.setCategoryUrl(t.getUrl());
						if(!ListUtils.checkDuplicatedItem(cateList, cate)){
							cateList.add(cate);
						}
					}
					
				}
				traverse(l1,cateList);
			}
			
			if(tLeaf !=null)
			{
				cateList = cateList.stream()
											.filter(c -> c.getCategoryString().replaceAll(" ", "").contains(tLeaf.getFilterString()))
											.collect(Collectors.toList());
			}
		}
		
		return cateList;
	}
	
	
	private Leaf getLastLeafWithUrl(Leaf f)
	{
		
		if(!StringUtils.isNullOrEmpty(f.getUrl()))
		{
			return f;
		}else {
			f = f.getParentLeaf();
			if(f!=null)
			{
				return getLastLeafWithUrl(f);
			}
		}
		
		return null;
	}
	
	/**
	 * get a list of category for best-item
	 * @return
	 */
	public List<Category> toBestItemList()
	{
		List<Leaf> oneDepth =  rootLeaf.getChildLeaf();
		List<Category> cateList = new ArrayList<>();
		
		if(oneDepth !=null && !oneDepth.isEmpty())
		{
			for(Leaf l1 : oneDepth)
			{
				if(l1.isBestItem() && !StringUtils.isNullOrEmpty(l1.getUrl()))
				{
					Category cate = new Category();
					cate.setCategoryNames(l1.getLeafNames());
					cate.setCategoryUrl(l1.getUrl());
					cateList.add(cate);
				}
				traverseForBestItem(l1,cateList);
			}
		}
		
		return cateList;
	}
	
	/**
	 * get a list of category for special exhibition
	 * @return
	 */
	public List<Category> toSpecialExhibitionList()
	{
		List<Leaf> oneDepth =  rootLeaf.getChildLeaf();
		List<Category> cateList = new ArrayList<>();
		
		if(oneDepth !=null && !oneDepth.isEmpty())
		{
			for(Leaf l1 : oneDepth)
			{
				if(l1.isSpecialExhibition() && !StringUtils.isNullOrEmpty(l1.getUrl()))
				{
					Category cate = new Category();
					cate.setCategoryNames(l1.getLeafNames());
					cate.setCategoryUrl(l1.getUrl());
					cateList.add(cate);
				}
				traverseForSpecialExhibition(l1,cateList);
			}
		}
		
		return cateList;
	}
	
	private void traverse(Leaf leaf,List<Category> cateList)
	{
		List<Leaf> children = leaf.getChildLeaf();
		
		if(children !=null && !children.isEmpty())
		{
			for(Leaf l : children)
			{
				if(!l.isLastLeaf())
				{
					traverse(l,cateList);
				}else 
				{
					if(!StringUtils.isNullOrEmpty(l.getUrl()))
					{
						Category cate = new Category();
						cate.setCategoryNames(l.getLeafNames());
						cate.setCategoryUrl(l.getUrl());
						cateList.add(cate);
						
					}else {
						
						Leaf t = getLastLeafWithUrl(l.getParentLeaf());
						
						if(t !=null)
						{
							Category cate = new Category();
							cate.setCategoryNames(t.getLeafNames());
							cate.setCategoryUrl(t.getUrl());
							if(!ListUtils.checkDuplicatedItem(cateList, cate)){
								cateList.add(cate);
							}
						}
					}
					
				}
				
				
			}
		}
	}
	
	private void traverseForBestItem(Leaf leaf,List<Category> cateList)
	{
		List<Leaf> children = leaf.getChildLeaf();
		
		if(children !=null && !children.isEmpty())
		{
			for(Leaf l : children)
			{
				if(!l.isBestItem())
				{
					traverseForBestItem(l,cateList);
				}else 
				{
					Category cate = new Category();
					cate.setCategoryNames(l.getLeafNames());
					cate.setCategoryUrl(l.getUrl());
					cateList.add(cate);
				}
			}
		}
	}
	
	
	
	
	private void traverseForSpecialExhibition(Leaf leaf,List<Category> cateList)
	{
		List<Leaf> children = leaf.getChildLeaf();
		
		if(children !=null && !children.isEmpty())
		{
			for(Leaf l : children)
			{
				if(!l.isSpecialExhibition())
				{
					traverseForSpecialExhibition(l,cateList);
				}else 
				{
					Category cate = new Category();
					cate.setCategoryNames(l.getLeafNames());
					cate.setCategoryUrl(l.getUrl());
					cateList.add(cate);
				}
			}
		}
	}


	@Override
	public String toString() {
		return "LeafTraverse [rootLeaf=" + rootLeaf + "]";
	}

}
