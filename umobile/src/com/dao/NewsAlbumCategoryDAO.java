package com.dao;

import java.util.List;

import com.model.news.album.NewsAlbumCategoryTree;
import com.model.news.album.NewsAlbumCategoryTreeGrid;

public interface NewsAlbumCategoryDAO {


	public abstract long findTreeGridTotal();

	public abstract List<NewsAlbumCategoryTreeGrid> findNewsAlbumCategoryTreeGrids();

	public abstract List<NewsAlbumCategoryTreeGrid> findNewsAlbumCategoryTreeGridByPid(
			Long pid);

	public abstract void saveTreeGridSet(NewsAlbumCategoryTreeGrid modelLazy);

	public abstract void deleteNewsAlbumCategoryTreeGridById(Long id);

	public abstract void updateTreeGridSet(
			NewsAlbumCategoryTreeGrid modelLazy);

	@SuppressWarnings("unchecked")
	public abstract List<NewsAlbumCategoryTree> findNewsAlbumCategoryTree();

}