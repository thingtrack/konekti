package com.thingtrack.konekti.dao.impl.internal;

import java.util.Calendar;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.api.SequenceDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.Sequence;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class SequenceDaoImpl extends JpaDao<Sequence, Integer> implements SequenceDao {

	@Override
	public Sequence getByName(String name) throws Exception {
		Sequence sequence = (Sequence)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.name = :name")
				.setParameter("name", name).getSingleResult();

		return sequence;
	}

	@Override
	public String setNextSequence(String code) throws Exception {
		// query last sequence
		Sequence sequence = (Sequence)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
				.setParameter("code", code).getSingleResult();

		// create custom sequence for the entity
		// <name><year><sequence>
		// where: <name>: Entity name
		//		  <year>: last two year digits
		//		  <sequence>: entity sequence with 5 digits
		String seqValue = sequence.getName() + 
						  Integer.toString(Calendar.getInstance().get(Calendar.YEAR)).substring(2, 4) + 
						  String.format("%05d", sequence.getValue());
		
		// update sequence
		sequence.setValue(sequence.getValue() + 1);
		save(sequence);
		
		return seqValue;
	}

}
