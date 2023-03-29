namespace BackendKengur.DTOComponents
{
    public class AssignmentDTO
    {

        public string TaskText { get; set; } = string.Empty;

        public IFormFile? TaskPicture { get; set; }

        public List<string> AnswersText { get; set; } = new List<string>();

        public List<IFormFile>? AnswersPictures { get; set; }

        public int CorrectAnswerIndex { get; set; } = 0;

        public int Level { get; set; } = 3;

        public string Class { get; set; } = string.Empty;
    }
}
