package am.ik.categolj.app.dao;

import java.util.List;

import am.ik.categolj.app.domain.Category;

public interface CategoryDao {
    List<Category> getAllCategoryList();

    Category getCategoryById(Long id);

    int getCategoryCountByNameAndIndex(String name, Long index);

    void insertCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Category category);
}
