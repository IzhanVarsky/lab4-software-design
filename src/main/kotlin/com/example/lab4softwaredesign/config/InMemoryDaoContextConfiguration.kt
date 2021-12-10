package com.example.lab4softwaredesign.config

import com.example.lab4softwaredesign.dao.ItemDao
import com.example.lab4softwaredesign.dao.ItemInMemoryDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InMemoryDaoContextConfiguration {
    @Bean
    fun itemDao(): ItemDao = ItemInMemoryDao()
}