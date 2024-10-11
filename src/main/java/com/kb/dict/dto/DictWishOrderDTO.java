package com.kb.dict.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictWishOrderDTO {
    List<DictWishOrder> dictWishOrders;
}
