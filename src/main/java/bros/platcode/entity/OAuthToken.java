package bros.platcode.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthToken {

    @EmbeddedId
    private OAuthTokenKey oAuthTokenKey;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private String expiration;

    @Embeddable
    @NoArgsConstructor
    public static class OAuthTokenKey implements Serializable {

        @ManyToOne(fetch = FetchType.LAZY)
        private Member member;

        @Column(nullable = false)
        private String service;

        @Builder
        public OAuthTokenKey(Member member, String service) {
            this.member = member;
            this.service = service;
        }
    }

    @Builder
    public OAuthToken(OAuthTokenKey oAuthTokenKey, String token, String expiration) {
        this.oAuthTokenKey = oAuthTokenKey;
        this.token = token;
        this.expiration = expiration;
    }
}
