
        }
    }

}

        H2Milestone dao = new H2Milestone();
        Milestone newML = new Milestone(
            request.getParameter("mlID"),
            request.getParameter("mlTitle"),
            request.getParameter("mlDescription"),
            request.getParameter("mlStatus"),
            request.getParameter("mlStartDate"),
            request.getParameter("mlDueDate"),
            request.getParameter("mlEndDate"),
            ptitle
        );
        System.out.println("the new milestone is "+ newML.getTitle());
        MilestoneDAO dao = new MilestoneDAO();
        request.setAttribute("title",ptitle);
//        String pID = new H2Project().getProject();
        H2Milestone dao = new H2Milestone();
        }
    }

}
