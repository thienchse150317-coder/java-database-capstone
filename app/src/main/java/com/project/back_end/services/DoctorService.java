import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Lấy tất cả khung giờ rảnh của bác sĩ trong ngày
    public List<LocalDateTime> getAvailableTimeSlots(Long doctorId, LocalDate date) {
        // giả sử bệnh viện làm từ 8h sáng đến 5h chiều, mỗi slot 1 tiếng
        List<LocalDateTime> allSlots = List.of(
            date.atTime(8, 0),
            date.atTime(9, 0),
            date.atTime(10, 0),
            date.atTime(11, 0),
            date.atTime(14, 0),
            date.atTime(15, 0),
            date.atTime(16, 0)
        );

        // lấy slot đã có hẹn từ bảng Appointment
        List<LocalDateTime> bookedSlots = appointmentRepository
                .findByDoctorIdAndDate(doctorId, date);

        // trả về slot còn trống
        return allSlots.stream()
                .filter(slot -> !bookedSlots.contains(slot))
                .toList();
    }
}
