package bros.platcode.repository;

import bros.platcode.entity.OAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthTokenRepository extends JpaRepository<OAuthToken, OAuthToken.OAuthTokenKey> {
}
