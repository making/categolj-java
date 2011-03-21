package am.ik.categolj.dao;

import java.util.List;

import am.ik.cateoglj.entity.Category;

public interface CategoryDao {
    List<Category> getAllCategoryList();

    Category getCategoryById(Long id);

    int getCategoryCountByNameAndIndex(String name, Long index);

    void insertCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Category category);
}
