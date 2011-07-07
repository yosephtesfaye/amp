package org.digijava.module.aim.util;

import java.sql.Connection;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.digijava.kernel.persistence.PersistenceManager;
import org.digijava.module.contentrepository.util.DocumentManagerUtil;
import org.hibernate.Session;

public class RepairDbUtil {
	private static Logger logger = Logger.getLogger(RepairDbUtil.class);
	
	public static void repairDb() {
		repairInexistentActivityCreators();
		repairInexistentPageInPageFilters();
	}
	
	/**
	 * Checks whether the AmpTeamMember that created the activity still exists. If there is a link from an activity 
	 * to an inexistent AmpteamMember then the field activity_creator is set to NULL
	 *
	 */
	public static void repairInexistentActivityCreators() {
		Session session = null;
		String qryStr = null;
		
		try{
				session				= PersistenceManager.getSession();
				Connection	conn	= session.connection();
				Statement st		= conn.createStatement();
				qryStr 				= "UPDATE amp_activity SET activity_creator=NULL WHERE activity_creator NOT IN (SELECT amp_team_mem_id FROM amp_team_member)" ;
				int result			= st.executeUpdate(qryStr);
				conn.close();
				
				if (result > 0) {
					logger.error ("There was an error with inexistent activity creator in AmpActivity --- updated " + result + "rows" );
				}
		}
	
		
		catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			ex.printStackTrace(System.out);
		}
		finally {
			if (session != null) {
				try {
					PersistenceManager.releaseSession(session);
				} catch (Exception rsf) {
					logger.error("Release session failed :" + rsf.getMessage());
				}
			}
		}
	}
	
	public static void repairInexistentPageInPageFilters() {
		Session session = null;
		String qryStr = null;
		
		try{
				session				= PersistenceManager.getSession();
				Connection	conn	= session.connection();
				Statement st		= conn.createStatement();
				qryStr 				= "DELETE FROM amp_team_page_filters WHERE page NOT IN (SELECT amp_page_id FROM amp_pages)" ;
				int result			=  st.executeUpdate(qryStr);
				conn.close();
				
				if (result > 0) {
					logger.error ("There was an error with inexistent amp_page in amp_team_page_filter --- deleted " + result + "rows" );
				}
		}
	
		
		catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			ex.printStackTrace(System.out);
		}
		finally {
			if (session != null) {
				try {
					PersistenceManager.releaseSession(session);
				} catch (Exception rsf) {
					logger.error("Release session failed :" + rsf.getMessage());
				}
			}
		}
	}
	
	public static void repairBannedUsersAreStillInATeam() {
		Session session = null;
		String qryStr = null;
		
		try{
				session				= PersistenceManager.getSession();
				Connection	conn	= session.connection();
				Statement st		= conn.createStatement();
				qryStr 				= "UPDATE dg_user SET dg_user.banned=0 WHERE banned=1  AND dg_user.id in (select amp_team_member.user_ from amp_team_member)" ;
				int result			=  st.executeUpdate(qryStr);
				conn.close();
				
				if (result > 0) {
					logger.error ("There was an error with banned users still appearing in teams --- updated " + result + "rows" );
				}
		}
	
		
		catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			ex.printStackTrace(System.out);
		}
		finally {
			if (session != null) {
				try {
					PersistenceManager.releaseSession(session);
				} catch (Exception rsf) {
					logger.error("Release session failed :" + rsf.getMessage());
				}
			}
		}
	}
	
	
	public static void repairDocumentNoLongerInContentRepository(String uuid, String className) {
		int numOfObjectsDeleted			= DocumentManagerUtil.deleteObjectsReferringDocument(uuid, className); 
		if ( numOfObjectsDeleted > 0 )
			logger.error ("There was an error with " + className + " using deleted documents. Deleting " + numOfObjectsDeleted + "rows" );
	}
}
