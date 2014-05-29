package com.dao;

import java.util.List;

import com.model.addressbook.AddressbookPublicTreeGrid;
import com.model.addressbook.AddressbookPublicTreeGrid;
import com.model.addressbook.AddressbookPublicTree;

public interface AddressbookPublicDAO {

	public abstract long findLazyTotal();

	public abstract List<AddressbookPublicTreeGrid> findAllLazy();

	public abstract List<AddressbookPublicTreeGrid> findLazyByPid(Long pid);

	public abstract List<AddressbookPublicTree> findAllTree();

	public abstract List<AddressbookPublicTree> findTreeRootByPid(Long pid);

	public abstract void saveLazy(AddressbookPublicTreeGrid modelLazy);

	public abstract void deleteById(Long id);

	public abstract void updateLazy(AddressbookPublicTreeGrid modelLazy);

	public abstract AddressbookPublicTreeGrid findAddressbookPublicTreeGridByName(
			String name);


}