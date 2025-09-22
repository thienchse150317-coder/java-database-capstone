@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TokenService tokenService; // service để validate token

    // Các CRUD method hiện có ...

    @GetMapping("/{id}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable Long id,
            @RequestParam String date,
            @RequestHeader("Authorization") String token) {

        // 1. Kiểm tra token
        if (!tokenService.isValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Invalid token");
        }

        // 2. Kiểm tra role (ví dụ chỉ admin hoặc patient được xem)
        String role = tokenService.getRoleFromToken(token);
        if (!role.equals("ADMIN") && !role.equals("PATIENT")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("Access denied");
        }

        // 3. Lấy danh sách thời gian rảnh của bác sĩ
        List<String> availableTimes = doctorService.getAvailableTimes(id, date);

        return ResponseEntity.ok(availableTimes);
    }
}
