package in.ineuron.service;

import java.util.List;

import in.ineuron.model.Tourist;

public interface ITouristService {

	public String saveTourist(Tourist t);

	public List<Tourist> fetchAllTourists();
	
	public Tourist fetchTouristById(Integer id);
	
	public String updateTourist(Tourist t);
	
	public String updateTouristBudget(Integer id, Float budgetPercent);
	
	public String deleteTourist(Integer id);
	
}
