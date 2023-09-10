package in.ineuron.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.exceptions.TouristNotFoundException;
import in.ineuron.model.Tourist;
import in.ineuron.repository.ITouristRepository;

/* Comparator(I) is used to perform to sorting on user-defined type objects, implementation can be given by
 * 1. Manual class - new ComparatorImpl().compare(t1, t2);
  
 * 2. Anonymous class - new Comparator({ // implementation class object of Comparator(I)
  								@Override
 								public int compare(Tourist t1, Tourist t2) {
 									;;;
 								}
 							});
 							
 * 3. Lambda Expression - (t1, t2) -> {	;;; }
 
@Service
public class ComparatorImpl implements Comparator<Tourist> {

	@Override
	public int compare(Tourist t1, Tourist t2) {
		// TODO Auto-generated method stub
		
		Integer id1=t1.getId();
		Integer id2=t2.getId();
		
		// Wrapper classes implements Comparable(I) & compareTo() is overridden
		// so, sorting two tourist objects on their ids behalf
		int n=id1.compareTo(id2);
		
		return n;
	}	
}
*/

@Service
public class TouristServiceImpl implements ITouristService {

	@Autowired
	private ITouristRepository repository;
	
	@Override
	public String saveTourist(Tourist t) {
		// TODO Auto-generated method stub
		
		Tourist savedTourist=repository.save(t);
		
		return "O/P : Tourist is registered successfully with the ID : " + savedTourist.getId();
	}

	@Override
	public List<Tourist> fetchAllTourists() {
		// TODO Auto-generated method stub
		
		List<Tourist> l=repository.findAll();
	
		// or just pass Comparator(I) implementation class object reference & it will sort all Tourist objects of list by calling compare() automatically
		l.sort( (t1, t2) -> {
				Integer id1=t1.getId();
				Integer id2=t2.getId();
			
				return id1.compareTo(id2);
			}
		);
		
		return l;
	}

	@Override
	public Tourist fetchTouristById(Integer id) {
		// TODO Auto-generated method stub
		
		/*Optional<Tourist> op=repository.findById(id);
		
		if(op.isPresent())
			return op.get();
		else	
			throw new TouristNotFoundException("O/P : Tourist Details Not Found for the ID : " + id);	// if tourist not found, then raise custom exception
		*/
		return repository.findById(id).orElseThrow( () -> new TouristNotFoundException("O/P : Tourist Details Not Found for the ID : " + id) );
	}

	@Override
	public String updateTourist(Tourist t) {
		// TODO Auto-generated method stub
		
		Optional<Tourist> op=repository.findById(t.getId());
		
		if(op.isPresent()) {
			repository.save(t);	// repository save() is used for insertion() and updation() both for Spring Data JPA but not for Spring Data Mongodb
			
			return "O/P : Tourist Updation is successful for the ID : " + t.getId();
		} else
			throw new TouristNotFoundException("O/P : Tourist Updation is not done as Not Found for the ID : " + t.getId() );
	}

	@Override
	public String updateTouristBudget(Integer id, Float budgetPercent) {
		// TODO Auto-generated method stub
		
		Optional<Tourist> op=repository.findById(id);
		
		if(op.isPresent()) {
			Tourist t=op.get();
			
			Double budget=t.getBudget();
			budget=budget + budget * budgetPercent * 0.01;
			
			t.setBudget(budget);
			
			repository.save(t);
			
			return "O/P : Tourist Budget is Updated successfully for the ID : " + id;
		} else
			throw new TouristNotFoundException("O/P : Tourist Budget Updation is not done as Not Found for the ID : " + id);
		
	}

	@Override
	public String deleteTourist(Integer id) {
		// TODO Auto-generated method stub
		
		Optional<Tourist> op=repository.findById(id);
		
		if(op.isPresent()) {
			repository.deleteById(id);
			
			return "O/P : Tourist Deletion is successful for the ID : " + id;
		} else
			throw new TouristNotFoundException("O/P : Tourist Deletion is not done as Not Found for the ID : " + id);
	}
	
}
