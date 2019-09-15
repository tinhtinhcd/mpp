package com.housing.app.repo;

import com.housing.app.model.Utility;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilitiesRepository extends BaseRepository<Utility, Long> {
    List<Utility> findUtilityByIdIn(long[] ids);
}
