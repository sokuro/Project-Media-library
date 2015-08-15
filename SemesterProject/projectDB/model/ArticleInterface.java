package projectDB.model;


import projectDB.model.Article;


public interface ArticleInterface {
	
	public void save();
	
	public void add(Article object);

	void loadXml();
	
}
