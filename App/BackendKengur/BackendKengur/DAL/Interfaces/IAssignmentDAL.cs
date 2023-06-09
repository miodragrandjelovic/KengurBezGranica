﻿using BackendKengur.Models;

namespace BackendKengur.DAL.Interfaces
{
    public interface IAssignmentDAL
    {
        Assignment AddNewAssignment(Assignment assignment);

        List<Assignment> GetAssignmentsByClass(string Class);
    }
}
