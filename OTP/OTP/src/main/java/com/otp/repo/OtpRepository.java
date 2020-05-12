package com.otp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.otp.model.Otp;
import com.otp.model.donnees.OtpStatus;

public interface OtpRepository extends JpaRepository<Otp, Long> {
	@Query(value = "select o from Otp o where o.otpnum=?1")
	Otp findOtpByValue(char[] otp);

	@Transactional
	@Modifying
	@Query(value = "delete from Otp o where o.dateExpiration< :date")
	void deletePhysic(@Param("date") java.sql.Timestamp date);

	@Query(value = "select o from Otp o where o.status=:status")
	List<Otp> getOtpByStatus(@Param("status") OtpStatus status);

}
