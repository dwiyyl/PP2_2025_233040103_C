<?php

use App\Http\Controllers\CategoryController;
use App\Http\Controllers\PostController;
use Illuminate\Support\Facades\Route;

// contoh route untuk menampilkan view
Route::get('/', function () {
    return view('welcome');
});

// Route untuk memanggil method di PostController
Route::get('posts', [PostController::class, 'index']);
Route::get('categories', [CategoryController::class, 'index']);