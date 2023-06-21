package com.example.torder;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.torder.repository.ContentRepository;
import com.example.torder.repository.JdbcContentRepository;
import com.example.torder.repository.JdbcMemberRepository;
import com.example.torder.repository.MemberRepository;
import com.example.torder.repository.MyPageUserRepository;
import com.example.torder.repository.NowContentRepository;
import com.example.torder.service.ContentService;
import com.example.torder.service.MemberService;
import com.example.torder.service.MyPageUserService;
import com.example.torder.service.NowContentService;

@Configuration
@EnableTransactionManagement
@MapperScan("com.example.torder.repository")
public class SpringConfig {
    private final DataSource dataSource;
    private final NowContentRepository nowcontentRepository;
    private final MyPageUserRepository myuserRepository;

    public SpringConfig(DataSource dataSource, NowContentRepository nowcontentRepository,
            MyPageUserRepository myPageUserRepository) {
        this.dataSource = dataSource;
        this.nowcontentRepository = nowcontentRepository;
        this.myuserRepository = myPageUserRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository(dataSource);
    }

    @Bean
    public ContentService contentService() {
        return new ContentService(contentRepository());
    }

    @Bean
    public ContentRepository contentRepository() {
        return new JdbcContentRepository(dataSource);
    }

    @Bean
    public NowContentService NowcontentService() {
        return new NowContentService(nowcontentRepository);
    }

    @Bean
    public MyPageUserService userService() {
        return new MyPageUserService(myuserRepository);
    }
}
