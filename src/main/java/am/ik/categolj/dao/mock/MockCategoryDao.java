package am.ik.categolj.dao.mock;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import am.ik.categolj.dao.CategoryDao;
import am.ik.cateoglj.entity.Category;

@Repository
public class MockCategoryDao implements CategoryDao {

    @Override
    public List<Category> getAllCategoryList() {
        Category c1 = new Category(1L, "xxx", 1L);
        Category c2 = new Category(2L, "yyy", 2L);
        return Arrays.asList(c1, c2);
    }

    @Override
    public Category getCategoryById(Long id) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public int getCategoryCountByNameAndIndex(String name, Long index) {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }

    @Override
    public void insertCategory(Category category) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void updateCategory(Category category) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void deleteCategory(Category category) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
