package com.backend.core.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by alan on 2014-06-22.
 */
public class MJdbcTokenRepositoryImpl extends JdbcTokenRepositoryImpl {

    public static final String CREATE_TABLE_SQL =
            "create table persistent_logins (email varchar(64) not null, series varchar(64) primary key, " +
                    "token varchar(64) not null, last_used timestamp not null)";
    public static final String DEF_TOKEN_BY_SERIES_SQL =
            "select email,series,token,last_used from persistent_logins where series = ?";
    public static final String DEF_INSERT_TOKEN_SQL =
            "insert into persistent_logins (email, series, token, last_used) values(?,?,?,?)";
    public static final String DEF_UPDATE_TOKEN_SQL =
            "update persistent_logins set token = ?, last_used = ? where series = ?";
    public static final String DEF_REMOVE_USER_TOKENS_SQL =
            "delete from persistent_logins where email = ?";

    private String tokensBySeriesSql = DEF_TOKEN_BY_SERIES_SQL;
    private String insertTokenSql = DEF_INSERT_TOKEN_SQL;
    private String updateTokenSql = DEF_UPDATE_TOKEN_SQL;
    private String removeUserTokensSql = DEF_REMOVE_USER_TOKENS_SQL;

    public void createNewToken(PersistentRememberMeToken token) {
        getJdbcTemplate().update(insertTokenSql, token.getUsername(), token.getSeries(),
                token.getTokenValue(), token.getDate());
    }

    public void updateToken(String series, String tokenValue, Date lastUsed) {
        getJdbcTemplate().update(updateTokenSql, tokenValue, new Date(), series);
    }

    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            return getJdbcTemplate().queryForObject(tokensBySeriesSql, new RowMapper<PersistentRememberMeToken>() {
                public PersistentRememberMeToken mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new PersistentRememberMeToken(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4));
                }
            }, seriesId);
        } catch(EmptyResultDataAccessException zeroResults) {
            if(logger.isDebugEnabled()) {
                logger.debug("Querying token for series '" + seriesId + "' returned no results.", zeroResults);
            }
        }catch(IncorrectResultSizeDataAccessException moreThanOne) {
            logger.error("Querying token for series '" + seriesId + "' returned more than one value. Series" +
                    " should be unique");
        } catch(DataAccessException e) {
            logger.error("Failed to load token for series " + seriesId, e);
        }

        return null;
    }

    public void removeUserTokens(String username) {
        getJdbcTemplate().update(removeUserTokensSql, username);
    }

}
