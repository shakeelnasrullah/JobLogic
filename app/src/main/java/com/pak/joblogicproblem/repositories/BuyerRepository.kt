package com.pak.joblogicproblem.repositories

interface BuyerRepository {

    suspend fun getBuyerList()
}

class BuyerRepositoryImpl() : BuyerRepository{

    override suspend fun getBuyerList() {
        TODO("Not yet implemented")
    }
}