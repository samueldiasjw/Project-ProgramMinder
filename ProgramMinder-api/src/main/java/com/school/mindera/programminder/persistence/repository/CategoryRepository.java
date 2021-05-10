package com.school.mindera.programminder.persistence.repository;

import com.school.mindera.programminder.persistence.entity.CategoryThreadsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryThreadsEntity, Long> {

}
