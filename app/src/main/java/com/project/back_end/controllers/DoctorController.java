@GetMapping("/doctors/{id}/availability/{date}/{token}")
public ResponseEntity<Map<String, Object>> getDoctorAvailability(
        @PathVariable("id") String doctorId,
        @PathVariable("date") String date,
        @PathVariable("token") String token) {

    Map<String, Object> response = new HashMap<>();

    try {
        // ✅ Kiểm tra token hợp lệ
        if (!tokenService.validateToken(token)) {
            response.put("status", "error");
            response.put("message", "Invalid token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // ✅ Lấy role từ token
        String role = tokenService.getRoleFromToken(token);
        if (!"admin".equalsIgnoreCase(role) && !"staff".equalsIgnoreCase(role)) {
            response.put("status", "error");
            response.put("message", "Access denied: insufficient permissions");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        // ✅ Lấy danh sách lịch trống
        List<Availability> availabilityList = doctorService.getAvailabilityByDoctorAndDate(doctorId, date);

        response.put("status", "success");
        response.put("doctorId", doctorId);
        response.put("date", date);
        response.put("availability", availabilityList);

        return ResponseEntity.ok(response);

    } catch (Exception e) {
        response.put("status", "error");
        response.put("message", "Server error: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
