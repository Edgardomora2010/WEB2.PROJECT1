package tec.nextshop_project_1.repository.jpa;

import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Category;
import tec.nextshop_project_1.repository.interfaces.ICategoryRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCategoryRepositoryAdapter implements ICategoryRepository {

    private final CategoryJpaRepository repository;

    public JpaCategoryRepositoryAdapter(CategoryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Category> findByCategoryName(String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            return Optional.empty();
        }
        return repository.findByNameIgnoreCase(categoryName.trim());
    }

    @Override
    public void addCategory(Category category) {
        repository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public void updateCategory(Category category) {
        repository.save(category);
    }

    @Override
    public boolean switchCategoryStatus(Long categoryId) {
        Optional<Category> category = repository.findById(categoryId);
        if (category.isEmpty()) {
            return false;
        }
        category.get().setActive(!category.get().isActive());
        repository.save(category.get());
        return true;
    }
}
