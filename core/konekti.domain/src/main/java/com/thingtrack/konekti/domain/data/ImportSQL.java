package com.thingtrack.konekti.domain.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;
import org.eclipse.persistence.sessions.UnitOfWork;

import com.thingtrack.konekti.domain.ProductType;

public class ImportSQL implements SessionCustomizer {

	@Override
	public void customize(Session session) throws Exception {

		session.getEventManager().addListener(new SessionEventAdapter() {

			@Override
			public void postLogin(SessionEvent event) {

				String fileName = (String) event.getSession().getProperty("import.xml.file");
				UnitOfWork unitOfWork = event.getSession().acquireUnitOfWork();
				
				File file;
				try {

					URL url = getClass().getResource(fileName);
					file = new File(url.getPath());
					
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

				// Text JAXB
				JAXBContext jaxbContext;
				try {
					jaxbContext = JAXBContext.newInstance(ProductType.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

					// output pretty printed
//					jaxbUnmarshaller.setProperty(
//							Marshaller.JAXB_FORMATTED_OUTPUT, true);
					
					ProductType productType = (ProductType) jaxbUnmarshaller.unmarshal(file);
					unitOfWork.registerNewObject(productType);
					unitOfWork.commit();

				} catch (JAXBException e) {
					throw new RuntimeException(e);
				}
			}

			@Override
			public void postAcquireClientSession(SessionEvent event) {

//				String fileName = (String) event.getSession().getProperty(
//						"import.sql.file");
//				UnitOfWork unitOfWork = event.getSession().acquireUnitOfWork();
//
//				importSQL(unitOfWork, fileName);
//
//				// Test
//				ProductType productType = new ProductType();
//				productType.setCode("Codigo de mi polla");
//				productType.setDescription("Descripciono de mi polla");
//				unitOfWork.registerNewObject(productType);
//
//				unitOfWork.commit();
			}
		});
	}

	private void importSQL(UnitOfWork unitOfWork, String fileName) {

		BufferedReader bufferedReader;
		try {

			URL url = getClass().getResource(fileName);
			File file = new File(url.getPath());
			bufferedReader = new BufferedReader(new FileReader(file));
			String sqlStatement = bufferedReader.readLine();

			unitOfWork.executeNonSelectingSQL(sqlStatement);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
