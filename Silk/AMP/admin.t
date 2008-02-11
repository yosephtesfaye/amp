﻿[-] testcase Test1_1() appstate DefaultBaseState
	[ ] login("admin@amp.org","admin")
	[ ] AMPAdmin.EventTypeManager.Click()
	[ ] AMPEventTypes.Name.SetText("New test event")
	[ ] AMPEventTypes.Color.SetText("#CC3366")
	[ ] AMPDesktopLinks.Add1.Click ()
	[ ] 
[-] testcase Test1_2() appstate DefaultBaseState
	[ ] login("atl@amp.org","atl")
	[ ] AMPDesktopLinks.TopMenu.Calendar.Click ()
	[ ] AMPCalendar.CreateNewEvent.Click ()
	[ ] AMPShowAmpEvent.EventTitle.SetText("new event type test")
	[ ] AMPShowAmpEvent.EventType.Select ("New test event")
	[ ] DATETIME today
	[ ] STRING date1
	[ ] today = GetDateTime()
	[ ] date1 = FormatDateTime(today,"dd/mm/yyyy")
	[ ] BrowserPage.ExecLine('document.getElementById("selectedStartDate").value = "'+date1+'"', TRUE)
	[ ] BrowserPage.ExecLine('document.getElementById("selectedEndDate").value = "'+date1+'"', TRUE)
	[ ] AMPShowAmpEvent.Preview.Click ()
	[ ] AMPDesktopLinks.Save.Click ()
	[ ] AMPCalendar.ShowPublicEvents.Check ()
	[ ] AMPCalendar.Show.Click ()
	[ ] 
[-] testcase Test1_3a() appstate DefaultBaseState
	[ ] login("admin@amp.org","admin")
	[ ] AMPAdmin.EventTypeManager.Click()
	[ ] INTEGER i
	[ ] STRING color = "eventTypeNameColor"
	[-] for (i = 1; i<8; i++)
		[ ] color[19] = Str(i)
		[ ] STRING elem = BrowserPage.ExecLine('document.getElementById("'+color+'").value')
		[-] if (elem == "#CC3366")
			[ ] BrowserPage.ExecLine('document.getElementById("'+color+'").value = "#CC3367"')
			[ ] break
		[ ] 
	[ ] AMPEventTypes.Save.Select(i)
	[ ] AMPEventTypes.Save.Click()
	[ ] 
[-] testcase Test1_3b() appstate DefaultBaseState
	[ ] login("admin@amp.org","admin")
	[ ] AMPAdmin.EventTypeManager.Click()
	[ ] INTEGER i
	[ ] STRING name = "eventTypeName"
	[-] for (i = 1; i<8; i++)
		[ ] name[14] = Str(i)
		[ ] STRING elem = BrowserPage.ExecLine('document.getElementById("'+name+'").value')
		[-] if (elem == "New test event")
			[ ] BrowserPage.ExecLine('document.getElementById("'+name+'").value = "New test event 2"')
			[ ] break
		[ ] 
	[ ] AMPEventTypes.Save.Select(i)
	[ ] AMPEventTypes.Save.Click()
	[ ] 
[-] testcase Test1_4() appstate DefaultBaseState
	[ ] login("atl@amp.org","atl")
	[ ] AMPDesktopLinks.TopMenu.Calendar.Click ()
	[ ] AMPCalendar.CreateNewEvent.Click ()
	[ ] AMPShowAmpEvent.EventTitle.SetText("new event type test")
	[ ] AMPShowAmpEvent.EventType.Select ("New test event 2")
	[ ] DATETIME today
	[ ] STRING date1
	[ ] today = GetDateTime()
	[ ] date1 = FormatDateTime(today,"dd/mm/yyyy")
	[ ] BrowserPage.ExecLine('document.getElementById("selectedStartDate").value = "'+date1+'"', TRUE)
	[ ] BrowserPage.ExecLine('document.getElementById("selectedEndDate").value = "'+date1+'"', TRUE)
	[ ] AMPShowAmpEvent.Preview.Click ()
	[ ] AMPDesktopLinks.Save.Click ()
	[ ] 
[-] testcase Test1_5() appstate DefaultBaseState
	[ ] login("admin@amp.org","admin")
	[ ] AMPAdmin.EventTypeManager.Click()
	[ ] INTEGER i
	[ ] STRING name = "eventTypeName"
	[-] for (i = 1; i<8; i++)
		[ ] name[14] = Str(i)
		[ ] STRING elem = BrowserPage.ExecLine('document.getElementById("'+name+'").value')
		[-] if (elem == "New test event")
			[ ] BrowserPage.ExecLine('document.getElementById("'+name+'").value = "New test event 2"')
			[ ] break
		[ ] 
	[ ] AMPEventTypes.Delete.Select(i)
	[ ] AMPEventTypes.Delete.Click()
	[ ] BrowserMessage.SetActive ()
	[ ] BrowserMessage.OK.Click ()
	[ ] 
[-] testcase Test2() appstate DefaultBaseState
	[ ] 
