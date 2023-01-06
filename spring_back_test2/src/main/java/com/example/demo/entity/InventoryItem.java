package com.example.demo.entity;

import com.example.demo.dto.InventoryItemDto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItem extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "inventory_item_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "inventory_id")
  private Inventory inventory;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @Column
  private int count;

  @Temporal(TemporalType.DATE)
  private Date regDate;


  @Temporal(TemporalType.DATE)
  private Date expDate;

  @Column
  private String storage;

  public static InventoryItem createInventoryItem(
    Inventory inventory,
    Item item,
    int count,
    String storage,
    Date expDate,
    Date regDate
  ) {
    return InventoryItem
      .builder()
      .inventory(inventory)
      .item(item)
      .count(count)
      .storage(storage)
      .expDate(expDate)
      .regDate(regDate)
      .build();
  }

  public void addCount(int count) {
    this.count += count;
  }

  public void change(InventoryItemDto inventoryItemDto) {
    InventoryItemDto.builder().count(inventoryItemDto.getCount());
  }
}
