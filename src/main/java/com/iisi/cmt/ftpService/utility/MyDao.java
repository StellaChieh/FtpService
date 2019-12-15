package com.iisi.cmt.ftpService.utility;

import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class MyDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public long writeFtpSuccess(String filename, LocalDateTime updatetime, String sourceFolder, String sourceFilename, String extension) {
		return writeFtpStatus(filename, updatetime, sourceFolder, sourceFilename, extension, true);
	}
	
	public long writeFtpFailed(String filename, LocalDateTime updatetime, String sourceFolder, String sourceFilename, String extension) {
		return writeFtpStatus(filename, updatetime, sourceFolder, sourceFilename, extension, false);
	}
	
	public void updateBackupPath(long dbRecordIndex, String backupPath) {
		String oSql = "UPDATE Ftp_Record SET backupPath = '%s' WHERE idx = '%d'";
		String hSql = String.format(oSql, backupPath, dbRecordIndex);
		jdbcTemplate.execute(hSql);
	}
	
	private long writeFtpStatus (String filename, LocalDateTime updatetime, String sourceFolder, String sourceFilename, String extension, boolean success) {
		String sql = "INSERT INTO Ftp_Record (filename, updatetime, sourceFolder, sourceFilename, extension, status) VALUES (?, ?, ?, ?, ? ,?);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection-> {
			java.sql.PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, filename);
			ps.setTimestamp(2, Timestamp.valueOf(updatetime));
			ps.setString(3, sourceFolder);
			ps.setString(4, sourceFilename);
			ps.setString(5, extension);
			ps.setBoolean(6, success);
			return ps;
		}, keyHolder);
		// return auto generated key
		return (long)keyHolder.getKey();
	}
		
}
