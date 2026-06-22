package com.anshul1507.composesamplefirst.practice.ui.screens.media

import android.content.Context
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.crossfade

object CoilConfig {

    fun createImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .memoryCache {
                // Memory LRU Cache Configuration (Keeps hot bitmap in fast RAM)
                MemoryCache.Builder()
                    .maxSizePercent(context, 0.25) // Allocate exactly 25% of overall app RAM ceiling
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCache {
                // Disk Cache configuration (Serializes network assets to internal storage)
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache_pipeline"))
                    .maxSizeBytes(50 * 1024 * 1024) // hard limit allocation to 50 Mb max space
                    .build()
            }
            .crossfade(true)
            .build()
    }
}