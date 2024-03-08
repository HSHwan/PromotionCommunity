package com.promotion_community.PromotionCommunity.repositories;

import com.promotion_community.PromotionCommunity.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
