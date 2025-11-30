<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    {{-- Tambahkan slot baru dengan $title --}}
    <title>{{ $title ?? 'Laravel App' }}</title>
</head>
<body>
    <nav>
        <a href="/">Home</a>
        <a href="/about">About</a>
        <a href="/blog">Blog</a>
        <a href="/contact">Contact</a>

    </nav>

    {{ $slot }}

    <footer style="margin-top: 20px;">
        © 2025 Praktikum Web – Universitas Pasundan
    </footer>


</body>
</html>