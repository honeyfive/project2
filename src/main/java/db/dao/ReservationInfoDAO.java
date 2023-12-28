package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.dto.ReservationInfoDTO;
import db.util.DBConnectionManager;
import oracle.sql.ConcreteProxyUtil;

public class ReservationInfoDAO {
	
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	//예약확인찾기 리스트
		//예약 전체 리스트
		public List<ReservationInfoDTO> findReservationInfoList() {

			conn = DBConnectionManager.connectDB();

			String sql = " select * from reservation_information";

			List<ReservationInfoDTO> reservationInfoList = null;

			try {
				psmt = conn.prepareStatement(sql);

				rs = psmt.executeQuery();
				reservationInfoList = new ArrayList<ReservationInfoDTO>();

				while (rs.next()) {
					ReservationInfoDTO reservationInfoDTO = new ReservationInfoDTO(
							rs.getInt("reservation_number"),rs.getString("rental_place"),rs.getString("return_place"),rs.getString("rental_date"),
							rs.getString("return_date"),rs.getInt("total_rental_date"),rs.getInt("total_rental_time"),
							rs.getInt("insurance_number"),rs.getString("car_number"),rs.getInt("membership_number"),rs.getInt("payment_number") );

					reservationInfoList.add(reservationInfoDTO);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnectionManager.closeDB(conn, psmt, rs);
			}

			return reservationInfoList;
		}
		
		
		//예약번호 기준으로 예약정보 한개만 읽어오는 메소드 
		public ReservationInfoDTO findReservationInfoByRsrvNumber(int reservation_number) {
			 conn = DBConnectionManager.connectDB();
			 String sql = "select * from reservation_information"
					 		+ "where reservation_number = ? " ;
			 
			 ReservationInfoDTO reservationInfoDTO = null; 	// return할 객체
			
			 try {
				 psmt = conn.prepareStatement(sql);
				 //connection 활용해서 sql 명령을 실행하는 객체
				 
				 psmt.setInt(1,reservation_number);
				 
				 rs = psmt.executeQuery(); 		//준비된 쿼리문 실행
				 
				 if(rs.next()) {
					 reservationInfoDTO = new ReservationInfoDTO (
							 rs.getInt("reservation_number"),rs.getString("rental_place"),rs.getString("return_place"),rs.getString("rental_date"),
								rs.getString("return_date"),rs.getInt("total_rental_date"),rs.getInt("total_rental_time"),
								rs.getInt("insurance_number"),rs.getString("car_number"),rs.getInt("membership_number"),rs.getInt("payment_number")
							 );
				 }
				 
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnectionManager.closeDB(conn, psmt, rs);
			}
			
			return reservationInfoDTO ;
		
}
	
		//아산지역 대여장소리스트
				public List<ReservationInfoDTO> findReservationInfoListByRentalPlaceAsan() {

					conn = DBConnectionManager.connectDB();

					String sql = " select rental_place from reservation_information "
							+ " where rental_place = '천안아산역 KTX 광장1' "
							+ " or rental_place = '선문대학교 본관 앞' "
							+ " or rental_place = '배방 농협 하나로 마트' "
							+ " or rental_place = '아산 고속버스터미널 앞' "
							+ " or rental_place = '탕정역 앞' "
							+ " or rental_place = '온양온천역' "
							+ " or rental_place = '순천향대 정문 앞' ";

					List<ReservationInfoDTO> reservationInfoListByRentalPlaceAsan = null;

					try {
						psmt = conn.prepareStatement(sql);

						rs = psmt.executeQuery();
						reservationInfoListByRentalPlaceAsan = new ArrayList<ReservationInfoDTO>();

						while (rs.next()) {
							ReservationInfoDTO reservationInfoDTO = new ReservationInfoDTO(rs.getString("rental_place"));

							reservationInfoListByRentalPlaceAsan.add(reservationInfoDTO);
						}

					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						DBConnectionManager.closeDB(conn, psmt, rs);
					}

					return reservationInfoListByRentalPlaceAsan;
				}
				
				//천안지역 대여장소리스트
				public List<ReservationInfoDTO> findReservationInfoListByRentalPlaceCheonan() {

					conn = DBConnectionManager.connectDB();

					String sql = " select rental_place from reservation_information where rental_place NOT IN('천안아산역 KTX 광장1', '선문대학교 본관 앞', '배방 농협 하나로 마트', '아산 고속버스터미널 앞', '탕정역 앞', '온양온천역', '순천향대 정문 앞') ";

					List<ReservationInfoDTO> reservationInfoListByRentalPlaceCheonan = null;

					try {
						psmt = conn.prepareStatement(sql);

						rs = psmt.executeQuery();
						reservationInfoListByRentalPlaceCheonan = new ArrayList<ReservationInfoDTO>();

						while (rs.next()) {
							ReservationInfoDTO reservationInfoDTO = new ReservationInfoDTO(rs.getString("rental_place"));

							reservationInfoListByRentalPlaceCheonan.add(reservationInfoDTO);
						}

					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						DBConnectionManager.closeDB(conn, psmt, rs);
					}

					return reservationInfoListByRentalPlaceCheonan;
				}
				
}
