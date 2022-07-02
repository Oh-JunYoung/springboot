package toyspringboot.server.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Dto.QnAListDto;
import toyspringboot.server.Domain.ResponseDto.QnAListResponseDto;
import toyspringboot.server.Domain.ResponseDto.QnAResponseDto;
import toyspringboot.server.Service.QnAService;
import toyspringboot.server.Slack.SlackService;

@Api(tags = {"QnA"})
@RestController
@RequiredArgsConstructor
public class QnAController {
    private final QnAService qnAService;
    private final SlackService slackService;

    @ApiOperation(value = "QnA 생성", notes = "QnA 게시글 생성 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "존재하지 않는 사용자 입니다."),
    })
    @PostMapping("/QnAs/QnA")
    public ResponseEntity<QnAResponseDto> createQnA(@RequestHeader("Authorization") String userToken, @RequestBody QnADto qnADto) {
        slackService.postSlackMessage("새로운 QnA가 생성되었습니다.");
        return ResponseEntity.ok()
                .body(qnAService.createQnA(userToken, qnADto).toResponse());
    }

    @ApiOperation(value = "QnA 조회", notes = "QnA 게시글 조회 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 403, message = "접근 권한이 없습니다."),
            @ApiResponse(code = 404, message = "존재하지 않는 사용자 입니다.")
    })
    @GetMapping("/QnAs/{QnAId}")
    public ResponseEntity<QnAResponseDto> readQnA(@RequestHeader("Authorization") String userToken,
                                         @PathVariable(value = "QnAId") Long qnAId) {
        return ResponseEntity.ok()
                .body(qnAService.readQnA(userToken, qnAId).toResponse());
    }

    @ApiOperation(value = "QnA 수정", notes = "QnA 게시글 수정 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 403, message = "접근 권한이 없습니다."),
            @ApiResponse(code = 404, message = "존재하지 않는 QnA 입니다.")
    })
    @PutMapping("/QnAs/QnA")
    public ResponseEntity<QnAResponseDto> updateQnA(@RequestHeader("Authorization") String userToken,
                          @RequestBody QnADto qnADto) {
        return ResponseEntity.ok()
                .body(qnAService.updateQnA(userToken, qnADto).toResponse());
    }


    @ApiOperation(value = "QnA 삭제", notes = "QnA 게시글 삭제 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 403, message = "삭제 권한이 없습니다."),
            @ApiResponse(code = 404, message = "존재하지 않는 QnA 입니다.")
    })
    @DeleteMapping("/QnAs/QnA")
    public ResponseEntity<QnAResponseDto> deleteQnA(@RequestHeader("Authorization") String userToken,
                          @RequestBody QnADto qnADto) {
        return ResponseEntity.ok()
                .body(qnAService.deleteQnA(userToken, qnADto).toResponse());
    }

    @ApiOperation(value = "QnA 검색", notes = "QnA 게시글 (제목, 내용) 검색 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success")
    })
    @GetMapping("/QnAs/QnA/search")
    public ResponseEntity<QnAListResponseDto> searchQnA(@RequestParam(value = "query") String query) {
        return ResponseEntity.ok()
                .body(qnAService.searchQnA(query).toResponse());
    }

    @ApiOperation(value = "QnA 리스트 페이지", notes = "QnA 페이지별 리스트 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success")
    })
    @GetMapping("/QnAs/QnA/page")
    public ResponseEntity<QnAListResponseDto>  QnAPage(@RequestParam(value = "page") Long page) {
        return ResponseEntity.ok()
                .body(qnAService.pageQnA(page).toResponse());
    }
}
