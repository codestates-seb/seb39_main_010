Required Secrets

# application-secrets.properties

spring.datasource.username= ${{ secrets.MYSQL_USERNAME }}

spring.datasource.password= ${{ secrets.MYSQL_PASSWORD }}

spring.datasource.url= jdbc:mysql://${{ secrets.AWS_RDS_ENDPOINT }}/main?useSSL=false\&characterEncoding=UTF-8\&serverTimezone=UTC+9

spring.security.oauth2.client.registration.google.clientId= ${{ secrets.GOOGLE_CLIENT_ID }}

spring.security.oauth2.client.registration.google.clientSecret= ${{ secrets.GOOGLE_CLIENT_SECRET }}

spring.security.oauth2.client.registration.naver.client-id= ${{ secrets.NAVER_CLIENT_ID }}

spring.security.oauth2.client.registration.naver.client-secret= ${{ secrets.NAVER_CLIENT_SECRET }}

spring.security.oauth2.client.registration.kakao.client-id= ${{ secrets.KAKAO_CLIENT_ID }}

spring.security.oauth2.client.registration.kakao.client-secret= ${{ secrets.KAKAO_CLIENT_SECRET }}

# secrets.properties

 Mail secrets
 
spring.mail.username= ${{ secrets.MAIL_SENDER_ID }}

spring.mail.password= ${{ secrets.MAIL_SENDER_PW }}

 Accepted domain
 
spring.config.domain= ${{ secrets.AWS_S3_Endpoint }}
