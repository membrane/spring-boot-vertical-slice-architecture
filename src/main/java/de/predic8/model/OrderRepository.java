package de.predic8.model;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {}