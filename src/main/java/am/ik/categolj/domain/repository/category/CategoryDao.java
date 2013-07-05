package am.ik.categolj.domain.repository.category;

import java.util.List;

import am.ik.categolj.domain.model.Category;

public interface CategoryDao {
    List<Category> getAllCategoryList();

    Category getCategoryById(Long id);

    int getCategoryCountByNameAndIndex(String name, Long index);

    void insertCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Category category);
}
