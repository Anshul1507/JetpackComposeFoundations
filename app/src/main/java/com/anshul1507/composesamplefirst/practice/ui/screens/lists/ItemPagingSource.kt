package com.anshul1507.composesamplefirst.practice.ui.screens.lists

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

data class PagedItem(
    val id: String,
    val name: String,
    val pageIndex: Int
)

class ItemPagingSource: PagingSource<Int, PagedItem> () {
    override fun getRefreshKey(state: PagingState<Int, PagedItem>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PagedItem> {
        val currentPage = params.key ?: 1

        return try {
            // simulate network backend latency
            delay(1200)

            val itemList = List(15) { index ->
                PagedItem(
                    id = "paged_${currentPage}_$index",
                    name = "Paginated Item $index (Page $currentPage)",
                    pageIndex = currentPage
                )
            }

            LoadResult.Page(
                data = itemList,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage == 5) null else currentPage + 1 // Cap fetch limit at page 5
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}