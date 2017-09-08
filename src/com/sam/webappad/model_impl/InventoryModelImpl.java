package com.sam.webappad.model_impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.sam.webappad.entity.InventarioEntity;
import com.sam.webappad.model.InventoryModelInterface;

@Transactional
@Repository
public class InventoryModelImpl implements InventoryModelInterface {

	@Override
	public List<InventarioEntity> showAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
